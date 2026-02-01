//
//  ErrorMessage.swift
//  iosApp
//
//  Created by Moamen Zalabya on 31/01/2026.
//

import SwiftUI

struct ErrorMessage: View {
    var message: String

    var body: some View {
        Text(message)
            .font(.title3)
            .multilineTextAlignment(.center)
            .padding()
    }
}
