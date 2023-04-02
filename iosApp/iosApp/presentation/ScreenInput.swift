//
//  ScreenInput.swift
//  iosApp
//
//  Created by ashutosh kailkhura on 01/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ScreenInput: View {
    
    @State var username: String = "philipplackner"
    
    var body: some View {
        
        NavigationView{
            
            VStack(alignment: .center) {
                
                //                NavigationLink (
                //                    destination: ScreenDetail(
                //                        viewModel: ScreenDetail.ViewModel(username:username)      )){
                //                            EmptyView()
                //                        }
                
                TextField("Enter username...", text: $username)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                
                NavigationLink("checkout"){
                    ScreenDetail(
                        viewModel: ScreenDetail.ViewModel(
                            username: username
                        )
                    )
                }
                
                Button(action: {
                    
                }) {
                    Text("Submit")
                        .padding(10.0)
                        .overlay(
                            RoundedRectangle(cornerRadius: 10.0)
                                .stroke(lineWidth: 2.0)
                                .shadow(color: .blue, radius: 10.0)
                        )
                }.padding(20.0)
                
            }.padding()
        }
    }
}

struct ScreenInput_Previews: PreviewProvider {
    static var previews: some View {
        ScreenInput()
    }
}
