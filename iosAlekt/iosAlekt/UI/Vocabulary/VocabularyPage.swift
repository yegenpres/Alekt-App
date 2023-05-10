
//
//  VocabularyPage.swift
//  iosAlectApp
//
//  Created by 111 on 23.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct VocabularyPage: View {
    private var viewModel = AlektApp.companion.vocaublaryViewModel
    
    var body: some View {
        ObservingView(statePublisher: statePublisher(viewModel.viewStates())) { model in
            ListOfWords(
                language: model.translateLanguage,
                languageOfSearch: model.searchLanguage,
                inSearch: model.isSearching,
                words: model.model) { _ in
                    viewModel.obtainEvent(viewEvent: .ChangeTranslateLang())
                } onChangeSearchLanguage: { lang in
                    viewModel.obtainEvent(viewEvent: .SetSearchLanguage(lang: lang))
                } onSearch: { query in
                    viewModel.obtainEvent(viewEvent: .Find(query: query))
                }
            
        }
    }
}


struct ListOfWords: View {
    @State private var _languageOfSearch: Languages = .dk
    @State private var query: String = ""
    
    let language: Languages
    let languageOfSearch: Languages
    let inSearch: Bool
    let words: Set<WordBase>
    let onTranslateLanguage: (Languages) -> Void
    let onChangeSearchLanguage: (Languages) -> Void
    let onSearch: (String) -> Void
    
    private var wordsGorups: [(String, [WordBase])] {
        let groupedDictionary = Dictionary(grouping: words) { item in
            String(item.wordDK.prefix(1))
        }
        
        return groupedDictionary.sorted { $0.0 < $1.0 }
    }
    
    init(language: Languages, languageOfSearch: Languages, inSearch: Bool, words: Set<WordBase>, onTranslateLanguage: @escaping (Languages) -> Void, onChangeSearchLanguage: @escaping (Languages) -> Void, onSearch: @escaping (String) -> Void) {
        self.language = language
        self.languageOfSearch = languageOfSearch
        self.inSearch = inSearch
        self.words = words
        self.onTranslateLanguage = onTranslateLanguage
        self.onChangeSearchLanguage = onChangeSearchLanguage
        self.onSearch = onSearch
    }
    
    var body: some View {
        let p = Binding<Languages>(get: {
                   return self._languageOfSearch
               }, set: {
                   self._languageOfSearch = $0
                   onChangeSearchLanguage($0)
               })
        
        NavigationStack {
            if inSearch {
                ProgressView()
            }
            
            if words.isEmpty {
                Text("Vocabulary is empty")
            }
            
            List {
                ForEach(wordsGorups, id: \.0) { (key, words) in
                    Section(header: Text(String(key))) {
                        ForEach(words, id: \.self) { word in
                            WordView(word: word, language: language)
                        }
                    }
                }
            }            .navigationTitle("Vocabulary")
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    AppBarPicker { lang in
                        onTranslateLanguage(lang)
                    }
                        .pickerStyle(DefaultPickerStyle())
                }
            }
        }
        .searchable(text: $query)
        .searchScopes(p) {
            SearchLangPicker()
        }
        .onChange(of: query) { _ in
            onSearch(query)
        }
        .onSubmit(of: .search) {
            
        }
        .indexViewStyle(.page(backgroundDisplayMode: .always))
    }
}


struct AppBarPicker: View {
    @State private var language: Languages
    var onChangeLanguage: (Languages) -> Void
    
    init(_ onChangeLanguage: @escaping (Languages) -> Void) {
        self.onChangeLanguage = onChangeLanguage
        self.language = .eng
    }

    var body: some View {
        let p = Binding<Languages>(get: {
                   return self.language
               }, set: {
                   self.language = $0
                   onChangeLanguage($0)
               })
        Picker("Translate to", selection: p) {
            Text("Eng").tag(Languages.eng)
            Text("Ru").tag(Languages.ru)
        }
    }
}


struct SearchLangPicker: View {
    
    var body: some View {
            Group {
                Text("Danish").tag(Languages.dk)
                Text("English").tag(Languages.eng)
                Text("Russia").tag(Languages.ru)
            }
    }
}

struct VocabularyPage_Preview: PreviewProvider {
    static var previews: some View {
        VocabularyPage()
    }
}

