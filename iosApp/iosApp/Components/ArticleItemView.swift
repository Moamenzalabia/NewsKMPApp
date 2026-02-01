//
//  ArticleItemView.swift
//  iosApp
//
//  Created by Moamen Zalabya on 31/01/2026.
//

import SwiftUI
import shared

struct ArticleItemView: View {
    var article: Article

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            AsyncImage(url: URL(string: article.imageUrl)) { phase in
                if phase.image != nil {
                    phase.image!
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                        .frame(height: 200)
                        .clipped()
                } else if phase.error != nil {
                    Rectangle()
                        .fill(Color.gray.opacity(0.3))
                        .frame(height: 200)
                } else {
                    HStack {
                        Spacer()
                        ProgressView()
                        Spacer()
                    }
                    .frame(height: 200)
                }
            }
            .cornerRadius(8)

            Text(article.title)
                .font(.headline)
                .fontWeight(.bold)

            Text(article.description_)
                .font(.body)
                .lineLimit(3)
                .foregroundColor(.secondary)

            Text(article.date)
                .font(.caption)
                .foregroundColor(.gray)
                .frame(maxWidth: .infinity, alignment: .trailing)
        }
        .padding(16)
    }
}
