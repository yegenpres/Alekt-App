//
//  iosAlektTestsLessons.swift
//  iosAlektTests
//
//  Created by 111 on 07.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import XCTest

@testable import iosAlekt
@testable import shared

final class iosAlectAppTestReadingRask: XCTestCase {
    
    lazy var repo: AWSReadingTaskProvider = {
        AWSReadingTaskProvider()
    }()

    override func setUpWithError() throws {
            }

    override func tearDownWithError() throws {
    }

    func testFetchList() async throws {
    let titles = try await repo.fetchListOfLesson()
        XCTAssertTrue(titles.count > 0)
    }
    
        
    func testFetchSingleLesson() async throws {
        let id = "dcd99d3f-da7e-452a-a3c0-9428745e5429"
        
        let item = try await repo.fetch(id: id)
        
        XCTAssertTrue(!item.isEmpty)

    }
    
    func testFetchLessonItems() async throws {
        let titles = try await repo.fetchListOfLesson()
        
        var lessonItems = [LessonBase]()
        
        for title in titles {
            let item = try await repo.fetch(id: title.id)
            
            guard !item.isEmpty else {
                XCTFail("\(item) dont fetched lesson")
                return
            }
            
            lessonItems.append(item.first!)
        }
        
        XCTAssertTrue(!titles.isEmpty && titles.count == lessonItems.count)
    }


    func testPerformanceExample() throws {
        // This is an example of a performance test case.
        measure {
            // Put the code you want to measure the time of here.
        }
    }

}


