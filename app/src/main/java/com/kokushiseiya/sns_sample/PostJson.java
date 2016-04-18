package com.kokushiseiya.sns_sample;

import java.util.List;

/**
 * Created by shunsuke on 16/04/18.
 */

/*
-KFdDhdBTdxU-vNQysa0={
 		text=東工大生です,
 		user={userId=subway, userName=Shunsuke Sasaki},
 		likeNum=0
 	}
 */
public class PostJson {

    List<Object> list;

    public String getText(){
        return (String)list.get(0);
    }

}
