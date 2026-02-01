import shared
import SwiftUI

struct AboutDeviceScreen: View {

    @Environment(\.dismiss)
      private var dismiss

    private struct RowItem: Hashable {
        let title: String
        let subtitle: String
    }

    private let items: [RowItem] = {
        let platform = Platform()

        var result: [RowItem] = [
            .init(
                title: "Operating System",
                subtitle: "\(platform.osName) \(platform.osVersion)"
            ),
            .init(
                title: "Device",
                subtitle: platform.deviceModel
            ),
            .init(
                title: "Density",
                subtitle: "@\(platform.density)x"
            )
        ]
        return result
    }()

    var body: some View {
        NavigationStack {
            List {
                ForEach(items, id: \.self) { item in
                    VStack(alignment: .leading) {
                        Text(item.title)
                            .font(.footnote)
                            .foregroundStyle(.secondary)
                        Text(item.subtitle)
                            .font(.body)
                            .foregroundStyle(.primary)
                    }
                    .padding(.vertical, 4)
                }
            }
            .navigationTitle("About Device")
            .toolbar {
                ToolbarItem(placement: .primaryAction) {
                    Button {
                        dismiss()
                    } label: {
                        Text("Done")
                            .bold()
                    }
                }
            }
        }
    }
}

#Preview {
    AboutDeviceScreen()
}
