//
//  ContentView.swift
//  iosApp
//
//  Created by Moamen Zalabya on 23/12/2025.
//

import SwiftUI
import shared

@MainActor
struct ContentView: View {

    // MARK: - Published State

    @State private var shouldOpenAbout = false
    @StateObject private var viewModel: ArticlesScreenViewModel

    // MARK: - Initialization

    init(viewModel: ArticlesScreenViewModel) {
        _viewModel = StateObject(wrappedValue: viewModel)
    }

    var body: some View {
        let articlesScreen = ArticlesScreen(viewModel: viewModel)
        NavigationStack{
            articlesScreen
                .toolbar {
                    ToolbarItem {
                        Button {
                            shouldOpenAbout = true
                        } label: {
                            Label("About", systemImage: "info.circle").labelStyle(.titleAndIcon)
                        }
                        .popover(isPresented: $shouldOpenAbout) {
                            AboutDeviceScreen()
                        }
                    }
                }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(viewModel: ArticlesScreenViewModel())
    }
}
