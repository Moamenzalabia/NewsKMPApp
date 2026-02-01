//
//  CategoriesViewModel.swift
//  iosApp
//
//  Created by Moamen Zalabya on 23/12/2025.
//

import shared
import Combine

@MainActor
class CategoriesViewModelWrapper: ObservableObject {

    // MARK: - Published State

    @Published var categoriesState: CategoriesState

    // MARK: - Private Properties

    let categoriesViewModel: CategoriesViewModel
    private var observingTask: Task<Void, Never>?

    // MARK: - Initialization

    init() {
        categoriesViewModel = CategoriesInjector().categoriesViewModel
        categoriesState = categoriesViewModel.categoriesState.value
    }

    deinit {
        observingTask?.cancel()
        categoriesViewModel.clear()
    }

    // MARK: - Internal Methods

    func startObserving() {
        observingTask?.cancel()
        observingTask = Task {
            for await categoriesS in categoriesViewModel.categoriesState {
                self.categoriesState = categoriesS
            }
        }
    }
}
