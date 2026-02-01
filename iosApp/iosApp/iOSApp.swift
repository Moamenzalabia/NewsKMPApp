import SwiftUI
import shared

@main
@MainActor
struct iOSApp: App {

    init() {
        KoinInitializerKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView(viewModel: ArticlesScreenViewModel())
        }
    }
}
