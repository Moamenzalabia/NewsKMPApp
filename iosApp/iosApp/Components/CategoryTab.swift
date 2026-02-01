//
//  CategoryTab.swift
//  iosApp
//
//  Created by Moamen Zalabya on 31/01/2026.
//

import SwiftUI
import shared

struct CategoryTab: View {
    var category: shared.Category
    var isSelected: Bool
    var onSelect: () -> Void
    
    var body: some View {
        Button(action: onSelect) {
            VStack(spacing: 4) {
                Text(category.name)
                    .font(.subheadline)
                    .fontWeight(.medium)
                    .foregroundColor(isSelected ? .primary : .secondary)
                
                Rectangle()
                    .fill(isSelected ? Color.primary : Color.clear)
                    .frame(height: 2)
            }
            .padding(.horizontal, 16)
            .padding(.vertical, 8)
        }
    }
}
