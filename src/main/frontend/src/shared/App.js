import React from 'react';
import {Route} from "react-router-dom";
import { Home, MemberListPage, LoginPage, AddMemberPage } from "../pages";

function App() {
    console.log("포트 백엔드 8080, 프론트 3000 첫 실행 -> App")
    return (
        <div>
            <Route exact path="/" component={Home} />
            <Route exact path="/api/members" component={MemberListPage} />
            <Route exact path="/api/members/login" component={LoginPage} />
            <Route exact path="/api/members/add" component={AddMemberPage} />
        </div>
    );
}
export default App;

