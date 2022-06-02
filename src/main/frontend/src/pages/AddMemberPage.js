import React from 'react';
import AuthTemplate from '../components/auth/AuthTemplate';
import AuthForm from '../components/auth/AuthForm';

const AddMemberPage = () => {
    return (
        <AuthTemplate>
            <AuthForm type='add' />
        </AuthTemplate>
    );
};

export default AddMemberPage;