import React from 'react';
import LoginTemplate from '../components/login/LoginTemplate';
import LoginForm from '../components/login/LoginForm';

const LoginPage = () => {
    return (
        <LoginTemplate>
            <LoginForm type='login' />
        </LoginTemplate>
    );
};

export default LoginPage;