import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel

    var body: some View {
        Text(viewModel.text)
    }
}

extension ContentView {
    class ViewModel: ObservableObject {
        @Published var text = "Loading..."
        init() {
//            let greet = ApiClient().getKMMText()
//            self.text = greet
            ApiClient().getUserInfo() { greeting, error in
                DispatchQueue.main.async {
                    if let greeting = greeting {
                        self.text = greeting.name
                    } else {
                        self.text = error?.localizedDescription ?? "error"
                    }
                }
            }
        }
    }
}
