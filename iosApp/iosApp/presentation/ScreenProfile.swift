//
//  ScreenProfile.swift
//  iosApp
//
//  Created by ashutosh kailkhura on 05/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ScreenProfile: View {
    
    var detail:DTOUserInfo
    
    var body: some View {
        ZStack(alignment: .top) {
            CardView {
                Spacer()
                VStack {
                    Text(detail.name)
                        .font(.title)
                        .fontWeight(.bold)
                        .padding(.top, 20)
                    
                    Text(detail.bio)
                        .font(.body)
                        .padding(.top, 10)
                    
                    Text(detail.location)
                        .font(.caption)
                        .foregroundColor(.gray)
                        .padding(.top, 5)
                }
                .padding()
            }
            .frame(maxWidth: .infinity)
            .background(Color.white)
            .cornerRadius(10)
            .shadow(radius: 5)
            .padding()
            
            AsyncImage(url: URL(string: "\(detail.avatar_url)"),scale: 2)
                .frame(width: 250, height: 250)
                .padding(.top, 50)
        }
    }
}

struct CardView<Content: View>: View {
    let content: () -> Content
    
    init(@ViewBuilder content: @escaping () -> Content) {
        self.content = content
    }
    
    var body: some View {
        VStack {
            Spacer()
            content()
            Spacer()
        }
    }
}


struct ScreenProfile_Previews: PreviewProvider {
    static var previews: some View {
        ScreenProfile(detail: DTOUserInfo(id: 123, avatar_url: "https://avatars.githubusercontent.com/u/1209554?v=4", followers_url: "String", repos_url: "", name: "rose", location: "XYZ", email: nil, bio: "Developer Advocate for Kotlin Multiplatform Mobile", public_repos: 0, public_gists: 0, followers: 0, following: 0, created_at: "03-March-2007"))
    }
}
