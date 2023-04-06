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
    
    @State var selection: Int? = nil
    @State var username: String = ""
    let sdk = ApiClient()
    
    var body: some View {
        
        NavigationView{
            VStack(alignment: .center) {
                
                TextField("Enter username...", text: $username)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                
                
                NavigationLink(destination: ScreenDetail(
                    viewModel: .init(sdk: sdk),username: $username
                ), tag: 1, selection: $selection) {
                    Button(action: {
                        self.selection = 1
                    }) {
                        HStack {
                            Spacer()
                            Text("checkout").foregroundColor(Color.white).bold()
                            Spacer()
                        }
                    }
                    .accentColor(Color.black)
                    .padding()
                    .background(Color(UIColor.darkGray))
                    .cornerRadius(4.0)
                    .padding(Edge.Set.vertical, 20)
                }
                
                
            }.padding(20.0)
        }
    }
}

struct ScreenInput_Previews: PreviewProvider {
    static var previews: some View {
        ScreenInput()
    }
}
