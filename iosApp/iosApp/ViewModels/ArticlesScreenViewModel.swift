//
//  ArticlesScreenViewModel.swift
//  iosApp
//
//  Created by Moamen Zalabya on 31/01/2026.
//

import Combine
import shared

@MainActor
class ArticlesScreenViewModel: ObservableObject {

    // MARK: - Published State

    @Published var error: String? = nil
    @Published var isLoading: Bool = false
    @Published var articles: [Article] = []
    @Published var selectedCategoryIndex: Int = 0
    @Published var categories: [shared.Category] = []

    // MARK: - Private Properties

    private var isFirstLoad = true
    private var cancellables = Set<AnyCancellable>()
    private let articlesWrapper = ArticlesViewModelWrapper()
    private let categoriesWrapper = CategoriesViewModelWrapper()

    // MARK: - Initialization

    init() {
        setupBindings()
    }

    // MARK: - Internal Methods

    func start() {
        articlesWrapper.startObserving()
        categoriesWrapper.startObserving()
    }

    func selectCategory(at index: Int) {
        guard index >= 0 && index < categories.count else { return }
        selectedCategoryIndex = index
        let category = categories[index]
        loadArticles(for: category, forceFetch: true)
    }

    func refresh() {
        guard !categories.isEmpty else { return }
        let category = categories[selectedCategoryIndex]
        loadArticles(for: category, forceFetch: true)
    }

    // MARK: - Private Methods

    private func setupBindings() {
        // Bind Categories State
        categoriesWrapper.$categoriesState
            .map { $0.categories }
            .assign(to: \.categories, on: self)
            .store(in: &cancellables)

        // Bind Articles State (Loading, Error, Content)
        articlesWrapper.$articlesState
            .sink { [weak self] state in
                self?.isLoading = state.loading
                self?.error = state.error
                self?.articles = state.articles
            }
            .store(in: &cancellables)

        // Handle First Load Logic
        categoriesWrapper.$categoriesState
            .map { $0.categories }
            .filter { !$0.isEmpty }
            .first()
            .sink { [weak self] categories in
                guard let self = self, self.isFirstLoad, let first = categories.first else { return }
                self.loadArticles(for: first)
                self.isFirstLoad = false
            }
            .store(in: &cancellables)
    }

    private func loadArticles(for category: shared.Category, forceFetch: Bool = false) {
        articlesWrapper.articlesViewModel.getArticles(
            forceFetch: forceFetch,
            country: category.country,
            category: category.category
        )
    }
}
