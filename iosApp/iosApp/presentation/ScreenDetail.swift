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
    @Binding var username:String
    
    var body: some View {
        VStack {
            switch viewModel.detail {
            case .loading:
                AnyView(
                    Text("loading ...").font(.largeTitle .weight(.bold))
                )
            case .result(let detail):
                AnyView(
                    ScreenProfile(detail: detail)
                )
            case .error(let errorMsg):
                AnyView(Text(errorMsg).font(.largeTitle .weight(.bold)))
                
            }
        }.onAppear(perform: {
            viewModel.getUserDetail(name: username)
        })
    }
    
}

extension ScreenDetail {
    
    enum UserDetailState {
        case loading
        case result(DTOUserInfo)
        case error(String)
    }
    
    class ViewModel: ObservableObject {
        
        
        let sdk:ApiClient
        
        @Published var detail = UserDetailState.loading
        
        init(sdk:ApiClient) {
            self.sdk=sdk
        }
        
        func getUserDetail(name:String){
            self.detail = UserDetailState.loading
            sdk.getDetail(name:name) { detail, error in
                DispatchQueue.main.async {
                    if let detail = detail {
                        self.detail = UserDetailState.result(detail)
                    } else {
                        self.detail = UserDetailState.error(error?.localizedDescription ?? "error")
                    }
                }
            }
        }
    }
}
