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
    @Binding var name:String
    
    var body: some View {
        
        
        
        switch viewModel.detail {
        case .loading:
            return AnyView(
                VStack{                    
                    Button(action: {
                        viewModel.getUserDetail()
                    }, label: {
                        Text("get detail for \(name)").font(.largeTitle .weight(.bold))
                    })
                }
            )
        case .result(let detail):
            return AnyView(
                VStack {
                    //                    AsyncImage(url: URL(string: userDetail?.avatar_url ?? ""),scale: 2)
                    //                        .frame(width: 128,height: 128)
                    //                        .clipShape(RoundedRectangle(cornerRadius: 25))
                    
                    Text(detail.name).font(.largeTitle .weight(.bold))
                    
                    //                    Button("Reload") {
                    //                        self.viewModel.getUserDetail()
                    //                    }
                }
            )
        case .error(let errorMsg):
            return AnyView(Text(errorMsg).font(.largeTitle .weight(.bold)))
        }
    }
    
}

extension ScreenDetail {
    
    enum UserDetailState {
        case loading
        case result(DTOUserInfo)
        case error(String)
    }
    
    class ViewModel: ObservableObject {
        
        //        let username:String
        let sdk:ApiClient
        
        @Published var detail = UserDetailState.loading
        
        init(sdk:ApiClient) {
            self.sdk=sdk
            //            self.username=username
        }
        
        func getUserDetail(){
            self.detail = UserDetailState.loading
            sdk.getDetail(name:"ashutoshkailkhura") { detail, error in
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
