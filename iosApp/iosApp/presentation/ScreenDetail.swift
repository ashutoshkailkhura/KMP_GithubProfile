//
//  ScreenDetail.swift
//  iosApp
//
//  Created by ashutosh kailkhura on 01/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ScreenDetail: View {
    
    @ObservedObject private(set) var viewModel: ViewModel
    
    var body: some View {
        VStack {
            Text(viewModel.name).font(.largeTitle .weight(.bold))
        }
    }
}

extension ScreenDetail {
    class ViewModel: ObservableObject {

        @Published var name = "Loading..."
        var username:String

        init(username:String) {
            self.username=username
            ApiClient().getDetail(name:username ) { data, error in
                DispatchQueue.main.async {
                    if let data = data {
                        self.name = data.name
                    } else {
//                        self.name = error?.localizedDescription ?? "error"
                        self.name = "error"
                    }
                }
            }
        }
    }
}
