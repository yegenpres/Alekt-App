
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

extension ReadingTask {
    func toBase() -> LessonBase {
        LessonBase(
            header: LessonTittleBase(id: self.id, title: self.title, subTitle: self.subTitle),
            text: self.text,
            soundUrl: SoundUrl(value: self.soundPath),
            level: self.level.toBase()
        )
    }
    
    func toTitleBase() -> LessonTittleBase {
        LessonTittleBase(id: self.id, title: self.title, subTitle: self.subTitle)
    }
}



class AWSReadingTaskProvider: LessonProvider {
    private let w = ReadingTask.keys
    
    override func fetch(id: String) async throws -> Set<LessonBase> {
        var result = Set<LessonBase>()
        do {
            
            let item = try await Amplify.DataStore.query(ReadingTask.self, byId: id)
                       
            if let item {
                result.insert(item.toBase())
            }
            
            return result
        } catch let error as DataStoreError {
            print("Error querying items: \(error)")
        } catch {
            print("Unexpected error: \(error)")
        }
        return result
       }

    override func fetchMedia(id: String) async throws -> Media {
        try await fetch(id: id).first?.soundUrl ?? SoundUrl(value: "")
    }

    override func fetchListOfLesson() async throws -> Set<LessonTittleBase> {
           var result = Set<LessonTittleBase>()

           do {
               let items = try await Amplify.DataStore.query(ReadingTask.self)
               for item in items {
                   result.insert(item.toTitleBase())
               }
           } catch let error as DataStoreError {
               print("Error querying items: \(error)")
           } catch {
               print("Unexpected error: \(error)")
           }
           
           return result
       }
  
}
