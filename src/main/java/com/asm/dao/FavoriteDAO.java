package com.asm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asm.entity.Favorite;

public interface FavoriteDAO extends JpaRepository<Favorite, Integer> {

}
