//
//  ArticlesViewModel.swift
//  iosApp
//
//  Created by Moamen Zalabya on 23/12/2025.
//

import shared
import Combine

@MainActor
class ArticlesViewModelWrapper: ObservableObject {
    
    // MARK: - Published State
    
    @Published var articlesState: ArticlesState
    
    // MARK: - Private Properties
    
    let articlesViewModel: ArticlesViewModel
    private var observingTask: Task<Void, Never>?
    
    // MARK: - Initialization
    
    init() {
        articlesViewModel = ArticlesInjector().articlesViewModel
        articlesState = articlesViewModel.articlesState.value
    }
    
    deinit {
        observingTask?.cancel()
        articlesViewModel.clear()
    }
    
    // MARK: - Internal Methods
    
    func startObserving() {
        observingTask?.cancel()
        observingTask = Task {
            for await articlesS in articlesViewModel.articlesState {
                self.articlesState = articlesS
            }
        }
    }
}
