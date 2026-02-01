//
//  ArticlesScreen.swift
//  iosApp
//
//  Created by Moamen Zalabya on 23/12/2025.
//

import SwiftUI
import shared

struct ArticlesScreen: View {

    @ObservedObject var viewModel: ArticlesScreenViewModel

    var body: some View {
        VStack(spacing: 0) {
            // Categories Row
            if !viewModel.categories.isEmpty {
                ScrollView(.horizontal, showsIndicators: false) {
                    HStack(spacing: 0) {
                        ForEach(Array(viewModel.categories.enumerated()), id: \.element.id) { index, category in
                            CategoryTab(
                                category: category,
                                isSelected: viewModel.selectedCategoryIndex == index,
                                onSelect: {
                                    viewModel.selectCategory(at: index)
                                }
                            )
                        }
                    }
                    .padding(.horizontal)
                }
                .padding(.bottom, 8)
            }
            // Articles Content
            ZStack {
                if viewModel.isLoading {
                    Loader()
                } else if let error = viewModel.error {
                    ErrorMessage(message: error)
                } else if !viewModel.articles.isEmpty {
                    ScrollView {
                        LazyVStack(spacing: 0) {
                            ForEach(viewModel.articles, id: \.self) { article in
                                ArticleItemView(article: article)
                                Divider()
                            }
                        }
                    }
                    .refreshable {
                        viewModel.refresh()
                    }
                } else {
                    ErrorMessage(message: "No articles found")
                }
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
        }
        .onAppear {
            viewModel.start()
        }
        .navigationTitle("Articles")
    }
}
