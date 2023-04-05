//
//  ScreenInput.swift
//  iosApp
//
//  Created by ashutosh kailkhura on 01/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ScreenInput: View {
    
    @State var username: String = "planetoftheweb"
    let sdk = ApiClient()
    
    var body: some View {
        
        NavigationView{
            VStack(alignment: .center) {
                
                TextField("Enter username...", text: $username)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                
                NavigationLink("checkout"){
                    ScreenDetail(
                        viewModel: .init(sdk: sdk),name: $username
                    )
                }.overlay(
                    RoundedRectangle(cornerRadius: 10.0)
                        .stroke(lineWidth: 1.0)
                        .shadow(color: .blue, radius: 10.0)
                        .padding(12.0)
                )
                
                
            }.padding(20.0)
        }
    }
}

struct ScreenInput_Previews: PreviewProvider {
    static var previews: some View {
        ScreenInput()
    }
}
