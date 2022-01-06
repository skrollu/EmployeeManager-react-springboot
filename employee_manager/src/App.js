import "./App.css";
import Header from "./components/Header";
import Footer from "./components/Footer";
import ListEmployee from "./components/ListEmployee";

function App() {
	return (
		<div className="App">
			<Header />
			<ListEmployee></ListEmployee>
			<Footer />
		</div>
	);
}

export default App;
