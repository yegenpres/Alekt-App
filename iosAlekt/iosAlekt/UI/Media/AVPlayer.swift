
//
//  Player.swift
//  iosAlectApp
//
//  Created by 111 on 08.04.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import AVFoundation
import Combine

class AVMediaPlayer: ObservableObject {
    private var player: AVPlayer?
    private var playerPeriodicObserver: Any?
    
    @Published var currentTimePublisher: Double = .init()
    @Published var currentProgressPublisher: Float = .init()
    
    var isPlaying: Bool {
        guard let player else { return false }
        if self.player?.timeControlStatus == .playing {
            return true
        } else {
            return false
        }
    }
    
    func setItem(url: String) {
        guard let url = URL(string: url) else { return }
        player = AVPlayer(url: url)
        guard let player else { return }
        setupPeriodicObservation(for: player)
        resetListener()
    }
    
    private func setupPeriodicObservation(for player: AVPlayer) {
        let timeScale = CMTimeScale(NSEC_PER_SEC)
        let time = CMTime(seconds: 0.5, preferredTimescale: timeScale)
        
        playerPeriodicObserver = player.addPeriodicTimeObserver(forInterval: time, queue: .main) { [weak self] (time) in
            guard let `self` = self else { return }
            let progress = self.calculateProgress(currentTime: time.seconds)
            self.currentProgressPublisher = progress
            self.currentTimePublisher = time.seconds
        }
    }
    
    private func calculateProgress(currentTime: Double) -> Float {
        return Float(currentTime / duration)
    }
    
    private func resetListener() {
        NotificationCenter.default.addObserver(forName: .AVPlayerItemDidPlayToEndTime, object: player?.currentItem, queue: nil) { _ in
            self.player?.seek(to: CMTime.zero)
        }
    }
    
    private var duration: Double {
        return player?.currentItem?.duration.seconds ?? 0
    }
    
    private func convertFloatToCMTime(_ percentage: Float) -> CMTime {
        return CMTime(seconds: duration * Double(percentage), preferredTimescale: CMTimeScale(NSEC_PER_SEC))
    }
    
    func play() {
        player?.play()
    }
    
    func pause() {
        player?.pause()
    }
    
    func seek(to time: CMTime) {
        player?.seek(to: time)
    }
    
    func seek(to percentage: Float) {
        let time = convertFloatToCMTime(percentage)
        player?.seek(to: time)
    }
 
}

class AVPlayerSliderViewModel: AVMediaPlayer {
    @Published var progressValue: Float = 0
    
    override func setItem(url: String) {
        super.setItem(url: url)
        listenToProgress()
    }
    
    var acceptProgressUpdates = true
    var subscriptions: Set<AnyCancellable> = .init()
    
    private func listenToProgress() {
        $currentProgressPublisher.sink { [weak self] progress in
            guard let self = self,
                  self.acceptProgressUpdates else { return }
            self.progressValue = progress
        }.store(in: &subscriptions)
    }
    
    func didSliderChanged(_ didChange: Bool) {
        acceptProgressUpdates = !didChange
        if didChange {
            pause()
        } else {
            seek(to: progressValue)
            play()
        }
    }
}
