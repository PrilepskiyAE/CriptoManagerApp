package com.prilepskiy.criptomanagerapp.data.repository

import android.util.Log
import com.prilepskiy.criptomanagerapp.data.dataservice.appservice.PreferenceService
import com.prilepskiy.criptomanagerapp.data.room.entity.UserEntity
import com.prilepskiy.criptomanagerapp.data.room.user.UserDataBase
import com.prilepskiy.criptomanagerapp.domain.repository.AuthorizationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.withContext

class AuthorizationRepositoryImpl(private val preferenceService: PreferenceService,private val userDB: UserDataBase):AuthorizationRepository {
    override fun getUser(): String? {
        return preferenceService.getToken()
    }

    override fun setUser(user:String) {
        preferenceService.setToken(user)
    }
   override suspend fun regestrationUser(login:String, pass:String, email:String):Boolean=
        withContext(Dispatchers.IO){
          var bloker:Boolean=false
          val usr= userDB.userDao.searchUsers(login)
            usr.collectLatest {
                it.forEach {
                    if (it.username == login)
                    {
                        bloker=true
                    }
                }
                if (!bloker){
                    Log.d("test", "regestrationUser: ")
                    userDB.userDao.insert(UserEntity(
                        username = login,
                        password = pass,
                        email = email,
                        favoriteCoinId =""
                    ))
                }
            }

            return@withContext bloker
        }



}