import React, { Component } from "react";
import EmployeeService from "../services/EmployeeService";

export default class ViewEmployee extends Component {
	constructor(props) {
		super(props);
		this.state = {
			id: this.props.match.params.id,
			employee: {},
		};
	}

	componentDidMount() {
		EmployeeService.getEmployeeById(this.state.id).then((res) => {
			this.setState({ employee: res.data });
		});
	}

	render() {
		return (
			<div>
				<br />
				<div className="card col-md-6 offset-md-3">
					<h3 className="text-center">View Employee details</h3>
					<div className="card-body">
						<div className="row">
							<label htmlFor="">First name</label>
							<div className=""> {this.state.employee.firstName} </div>
						</div>
						<div className="row">
							<label htmlFor="">Last name</label>
							<div className=""> {this.state.employee.lastName} </div>
						</div>
						<div className="row">
							<label htmlFor="">Email Id</label>
							<div className=""> {this.state.employee.emailId} </div>
						</div>
					</div>
				</div>
			</div>
		);
	}
}
