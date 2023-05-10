//
//  iosAlektTests.swift
//  iosAlektTests
//
//  Created by 111 on 07.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import XCTest

@testable import iosAlekt
@testable import shared

final class iosAlectAppUnitTests: XCTestCase {
    
    lazy var wordsRepo: AWSWordProvider = {
        AWSWordProvider()
    }()

    override func setUpWithError() throws {
            }

    override func tearDownWithError() throws {
    }

    func testRepositoryIsNotEMpty() async throws {
  
        let words = try await wordsRepo.fetch()
        
        
        
        XCTAssertTrue(words.count > 0)
    }

    func testPerformanceExample() throws {
        // This is an example of a performance test case.
        measure {
            // Put the code you want to measure the time of here.
        }
    }

}



