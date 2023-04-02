//
//  MainViewModel.swift
//  iosApp
//
//  Created by ashutosh kailkhura on 01/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class MainViewModel: ObservableObject {
    
    var urlString: String
    @Published var text = "Loading..."
    
    init(urlString: String) {
        self.urlString = urlString
//        if urlString.isEmpty {
//            self.urlString = "kunal-kushwaha"
//        }
        ApiClient().getDetail(name: urlString) { detail, error in
            DispatchQueue.main.async {
                if let detail = detail {
                    self.text = detail.name
                } else {
                    self.text = error?.localizedDescription ?? "error"
                }
            }
        }
    }
}
