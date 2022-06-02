import React, {Component} from 'react';
import {Route} from "react-router-dom";
import { Home, MemberListPage, LoginPage, AddMemberPage } from "../pages";

class App extends Component {
    render() {
        return (
            <div>
                <Route exact path="/" component={Home} />
                <Route path="/api/members" component={MemberListPage} />
                <Route path="/api/members/login" component={LoginPage} />
                <Route path="/api/members/add" component={AddMemberPage} />
            </div>
        );
    }
}

export default App;

