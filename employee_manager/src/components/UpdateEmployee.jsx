import React, { Component } from "react";
import EmployeeService from "../services/EmployeeService";

class UpdateEmployee extends Component {
	constructor(props) {
		super(props);
		this.state = {
			id: this.props.match.params.id,
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

	componentDidMount() {
		EmployeeService.getEmployeeById(this.state.id).then((res) => {
			let employee = res.data;
			this.setState({
				firstName: employee.firstName,
				lastName: employee.lastName,
				emailId: employee.emailId,
			});
		});
	}

	saveEmployee = (e) => {
		e.preventDefault();
		let employee = {
			id: this.state.id,
			firstName: this.state.firstName,
			lastName: this.state.lastName,
			emailId: this.state.emailId,
		};

		console.log(employee);
		EmployeeService.updateEmployee(employee, employee.id).then((res) => {
			console.log(res.data);
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
							<h3 className="text-center">Update Employee</h3>
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

export default UpdateEmployee;
