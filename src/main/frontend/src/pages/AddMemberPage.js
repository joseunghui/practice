import React from 'react';
import AddTemplate from '../components/add/AddTemplate';
import AddForm from '../components/add/AddMemberForm';

const AddMemberPage = () => {
    return (
        <AddTemplate>
            <AddForm type='add' />
        </AddTemplate>
    );
};

export default AddMemberPage;