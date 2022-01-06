import "./App.css";
import Header from "./components/Header";
import Footer from "./components/Footer";
import ListEmployee from "./components/ListEmployee";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import CreateEmployee from "./components/CreateEmployee";
import UpdateEmployee from "./components/UpdateEmployee";
import ViewEmployee from "./components/ViewEmployee";

function App() {
	return (
		<Router>
			<Header />
			<div className="container">
				<Switch>
					<Route path="/" exact component={ListEmployee} />
					<Route path="/employees" component={ListEmployee} />
					<Route path="/add/employee" component={CreateEmployee} />
					<Route path="/update/employee/:id" component={UpdateEmployee} />
					<Route path="/employee/:id" component={ViewEmployee} />
				</Switch>
			</div>
			<Footer />
		</Router>
	);
}

export default App;
