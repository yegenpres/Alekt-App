
//
//  AWSWordProvider.swift
//  iosAlectApp
//
//  Created by 111 on 10.04.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import AWSAPIPlugin
import AWSDataStorePlugin
import Amplify
import shared

extension Level {
    
    func toBase() -> LevelBase {
         switch (self) {
        case .a1:
            return LevelBase.a1
        case .a2:
           return LevelBase.a2
        case .b1:
            return LevelBase.b1
        case .b2:
            return LevelBase.b2
        case .c1:
            return LevelBase.c1
        case .c2:
            return LevelBase.c2
        }
    }
}

extension Word {
    func toBase() -> WordBase {
        WordBase(
            id: self.id,
            wordDK: self.wordDK,
            transcription: self.transcription ?? "",
            translateEng: self.translateEng,
            translateRu: self.translateRu,
            level: self.level.toBase(),
            audio: SoundUndBase64(value: self.soundPath ?? "")
        )
    }
}

class AWSWordProvider: WordsProvider {
    private let w = Word.keys
    
    override func fetch() async throws -> Set<WordBase> {
         await query {
              try await $0.query(Word.self)
        }
    }
    
    override func fetch(start: Int32, end: Int32) async throws -> Set<WordBase> {
        await query {
            try await $0.query(Word.self, paginate: .page(UInt(start), limit: UInt(end)))
       }
    }
    
    override func fetchMatchDK(wordDk: String) async throws -> Set<WordBase> {
        await query {
            try await $0.query(Word.self, where: self.w.wordDK.contains(wordDk))
       }
    }
    
    override func fetchEqualDK(wordDk: String) async throws -> Set<WordBase> {
        await query {
            try await $0.query(Word.self, where: self.w.wordDK.eq(wordDk))
       }
    }
    
    
    override func fetchMatchDK(wordDk: String, start: Int32, end: Int32) async throws -> Set<WordBase> {
        await query {
            try await $0.query(
                Word.self,
                where: self.w.wordDK.contains(wordDk),
                paginate: .page(UInt(start), limit: UInt(end))
            )
       }
    }
    
    override func fetchMatchENG(english: String) async throws -> Set<WordBase> {
        await query {
            try await $0.query(Word.self, where: self.w.translateEng.contains(english))
       }
    }
    
    override func fetchMatchENG(english: String, start: Int32, end: Int32) async throws -> Set<WordBase> {
        await query {
            try await $0.query(
                Word.self,
                where: self.w.wordDK.contains(english),
                paginate: .page(UInt(start), limit: UInt(end))
            )
       }
    }
    
    override func fetchMatchRU(Ru: String) async throws -> Set<WordBase> {
        await query {
            try await $0.query(Word.self, where: self.w.translateRu.contains(Ru))
       }
    }

    override func fetch(id: String) async throws -> Set<WordBase> {
        var result = Set<WordBase>()
        do {
            
            let item = try await Amplify.DataStore.query(Word.self, byId: id)
           
            if let item {
                result.insert(item.toBase())
            }
            
            return Set()
        } catch let error as DataStoreError {
            print("Error querying items: \(error)")
        } catch {
            print("Unexpected error: \(error)")
        }
        return result
    }

    override func fetchMedia(id: String) async throws -> SoundUndBase64 {
        var result = SoundUndBase64(value: "")
        
        do {
            
            let item = try await Amplify.DataStore.query(Word.self, byId: id)
           
            if let path = item?.soundPath {
                result = SoundUndBase64(value: path)
            }
            
            return SoundUndBase64(value: "")
        } catch let error as DataStoreError {
            print("Error querying items: \(error)")
        } catch {
            print("Unexpected error: \(error)")
        }
        
        return result
    }
}

private func query(query: (DataStoreCategory) async throws -> [Word] ) async -> Set<WordBase> {
    var result = Set<WordBase>()

    do {
        let items = try await query(Amplify.DataStore)
        for item in items {
            result.insert(item.toBase())
        }
    } catch let error as DataStoreError {
        print("Error querying items: \(error)")
    } catch {
        print("Unexpected error: \(error)")
    }
    
    return result
}
