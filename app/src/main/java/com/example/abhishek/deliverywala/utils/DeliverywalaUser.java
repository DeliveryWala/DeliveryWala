package com.example.abhishek.deliverywala.utils;

import com.backendless.BackendlessUser;

/**
 * Created by Abhishek on 07-05-2016.
 */
    public class DeliverywalaUser extends BackendlessUser
    {
        public String getEmail()
        {
            return super.getEmail();
        }

        public void setEmail( String email )
        {
            super.setEmail( email );
        }

        public String getPassword()
        {
            return super.getPassword();
        }

        public String getName()
        {
            return (String) super.getProperty( "name" );
        }

        public void setName( String name )
        {
            super.setProperty( "name", name );
        }
    }
