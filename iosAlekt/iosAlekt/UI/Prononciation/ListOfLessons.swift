
//
//  ListOfLessons.swift
//  iosAlectApp
//
//  Created by 111 on 15.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct ListOfLessons: View {
    var items = [1,2,3,4,5,6,7,8,9]
    
    var body: some View {
        NavigationStack {
            List(items, id: \.self) { item in
                    NavigationLink {
                        PrononsLesson(id: item)
                        
                    } label: {
                        Text("title \(item)")
                    }
                }
                    .navigationTitle("Landmarks")
                
              }
    }
}
