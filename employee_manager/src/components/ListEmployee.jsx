import React, { Component } from "react";
import EmployeeService from "../services/EmployeeService";

export default class ListEmployee extends Component {
	constructor(props) {
		super(props);
		this.state = {
			employees: [],
		};

		this.addEmployee = this.addEmployee.bind(this);
		this.editEmployee = this.editEmployee.bind(this);
		this.deleteEmployee = this.deleteEmployee.bind(this);
		this.viewEmployee = this.viewEmployee.bind(this);
	}

	componentDidMount() {
		EmployeeService.getEmployees().then((res) => {
			this.setState({
				employees: res.data,
			});
		});
	}

	addEmployee() {
		this.props.history.push("/add/employee");
	}

	editEmployee(id) {
		this.props.history.push(`/update/employee/${id}`);
	}

	deleteEmployee(id) {
		EmployeeService.deleteEmployee(id).then((res) => {
			this.setState({
				employees: this.state.employees.filter((employee) => employee.id !== id),
			});
		});
	}

	viewEmployee(id) {
		this.props.history.push(`/employee/${id}`);
	}

	render() {
		return (
			<div>
				<h2 className="text-center">Employees list</h2>
				<div className="row">
					<button className="btn btn-primary" onClick={this.addEmployee}>
						Add Employee
					</button>
				</div>
				<div className="row">
					<table className="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Employee first name</th>
								<th>Employee last name</th>
								<th>Employee emailId name</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							{this.state.employees.map((employee) => (
								<tr key={employee.id}>
									<td>{employee.firstName}</td>
									<td>{employee.lastName}</td>
									<td>{employee.emailId}</td>
									<td>
										<button
											className="btn btn-info"
											onClick={() => this.editEmployee(employee.id)}
										>
											Update
										</button>
										<button
											className="btn btn-danger"
											style={{ marginLeft: "20px" }}
											onClick={() => this.deleteEmployee(employee.id)}
										>
											Delete
										</button>
										<button
											className="btn btn-success"
											style={{ marginLeft: "20px" }}
											onClick={() => this.viewEmployee(employee.id)}
										>
											View
										</button>
									</td>
								</tr>
							))}
						</tbody>
					</table>
				</div>
			</div>
		);
	}
}
