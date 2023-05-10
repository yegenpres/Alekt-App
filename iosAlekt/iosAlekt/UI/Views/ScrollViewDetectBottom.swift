
//
//  ScrollViewDeteckButtom.swift
//  iosAlectApp
//
//  Created by 111 on 02.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

private struct ScrollOffsetPreferenceKey: PreferenceKey {
    static var defaultValue: CGFloat = .zero

    static func reduce(value: inout CGFloat, nextValue: () -> CGFloat) {
    }
}


private extension CoordinateSpace {
    static var scrollToBottom: Self {
        self.named("scrollToBottomView_")
    }
}


struct ViewOnBottomFooter<Content: View, Footer: View>: View {
    private let coordinameName = "ScrollToBottomView_"
    @State private var isOnBottom: Bool = false
    
    @ViewBuilder let content: () -> Content
    @ViewBuilder let footer: (Bool) -> Footer

    
    var body: some View {
        GeometryReader { geo1 in
            ZStack(alignment: .bottom) {
                ScrollView {
                    VStack {
                        content()
                        }
                    .background(GeometryReader { geo2 in
                        let offset = geo2.frame(in: .scrollToBottom).maxY
                        Color.clear.preference(key: ScrollOffsetPreferenceKey.self, value: offset)
                    })
                    .coordinateSpace(name: CoordinateSpace.scrollToBottom)
                    }
                .onPreferenceChange(ScrollOffsetPreferenceKey.self) { value in
                    let safearea = geo1.safeAreaInsets.top
                    isOnBottom = geo1.size.height > value - safearea
                                }
                footer(isOnBottom)
            }
        }
    }
}

struct ViewOnBottomFooter_Preview: PreviewProvider {
    static var previews: some View {
        Group {
            ViewOnBottomFooter {
                    ForEach(0...20, id: \.self) { id in
                        Text("text \(id)")
                            .padding(20)
                    }
            } footer : { isOnBottom in
                Color(uiColor: isOnBottom ? .blue: .gray)
                    .frame(height: 30)
                    
            }
        }
    }
}

extension View {
    func printInView(_ value: Any) -> Self {
        Swift.print(value)
        return self
    }
}

func runCode(_ code: @autoclosure () -> Any) -> EmptyView { EmptyView() }
