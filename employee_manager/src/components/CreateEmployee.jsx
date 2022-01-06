import React, { Component } from "react";
import EmployeeService from "../services/EmployeeService";

export default class CreateEmployee extends Component {
	constructor(props) {
		super(props);
		this.state = {
			firstName: "",
			lastName: "",
			emailId: "",
		};

		this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
		this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
		this.changeEmailIdHandler = this.changeEmailIdHandler.bind(this);

		this.saveEmployee = this.saveEmployee.bind(this);
	}

	changeFirstNameHandler = (e) => {
		this.setState({
			firstName: e.target.value,
		});
	};

	changeLastNameHandler = (e) => {
		this.setState({
			lastName: e.target.value,
		});
	};

	changeEmailIdHandler = (e) => {
		this.setState({
			emailId: e.target.value,
		});
	};

	saveEmployee = (e) => {
		e.preventDefault();
		let employee = {
			firstName: this.state.firstName,
			lastName: this.state.lastName,
			emailId: this.state.emailId,
		};

		EmployeeService.createEmployee(employee).then((res) => {
			this.props.history.push("/employees");
		});
	};

	cancel() {
		this.props.history.push("/employees");
	}

	render() {
		return (
			<div>
				<div className="container">
					<div className="row">
						<div className="card col-md-6 offset-md-3 offset-md-3">
							<h3 className="text-center">Add Employee</h3>
							<div className="card-body">
								<form>
									<div className="form-group">
										<label htmlFor="inputFirstName">First name: </label>
										<input
											id="inputFirstName"
											placeholder="First name"
											name="firstName"
											className="form-control"
											value={this.state.firstName}
											onChange={this.changeFirstNameHandler}
										/>
									</div>
									<div className="form-group">
										<label htmlFor="inputLastName">Last name: </label>
										<input
											id="inputLastName"
											placeholder="Last name"
											name="lastName"
											className="form-control"
											value={this.state.lastName}
											onChange={this.changeLastNameHandler}
										/>
									</div>
									<div className="form-group">
										<label htmlFor="inputEmailId">EmailId name: </label>
										<input
											id="inputEmailId"
											placeholder="Email id"
											name="EmailId"
											className="form-control"
											value={this.state.emailId}
											onChange={this.changeEmailIdHandler}
										/>
									</div>
									<button className="btn btn-success" onClick={this.saveEmployee}>
										Save
									</button>
									<button
										className="btn btn-danger"
										onClick={this.cancel.bind(this)} //directly bind the method to the class
										style={{ marginLeft: "10px" }}
									>
										Cancel
									</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		);
	}
}
