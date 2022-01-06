import React, { Component } from "react";
import { Link } from "react-router-dom";

export default class Header extends Component {
	render() {
		return (
			<>
				<header>
					<nav className="navbar navbar-expand-mb navbar-dark bg-dark">
						<div className="header">
							<a href="https://github.com/skrollu/">Skrollu</a>

							<Link to="/employees">Welcome</Link>
							<Link to="/add/employee">Add</Link>
						</div>
					</nav>
				</header>
			</>
		);
	}
}
