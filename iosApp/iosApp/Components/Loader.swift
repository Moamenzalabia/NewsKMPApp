//
//  Loader.swift
//  iosApp
//
//  Created by Moamen Zalabya on 31/01/2026.
//

import SwiftUI

struct Loader: View {
    
    var body: some View {
        ProgressView()
            .progressViewStyle(CircularProgressViewStyle())
            .scaleEffect(1.5)
    }
}
