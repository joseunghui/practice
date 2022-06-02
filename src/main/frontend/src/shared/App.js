import React, {Component} from 'react';
import {Route} from "react-router-dom";
import { Home, MemberListPage, LoginPage, AddMemberPage } from "../pages";

class App extends Component {
    render() {
        return (
            <div>
                <Route exact path="/" component={Home} />
                <Route exact path="/api/members" component={MemberListPage} />
                <Route exact path="/api/members/login" component={LoginPage} />
                <Route exact path="/api/members/add" component={AddMemberPage} />
            </div>
        );
    }
}

export default App;

