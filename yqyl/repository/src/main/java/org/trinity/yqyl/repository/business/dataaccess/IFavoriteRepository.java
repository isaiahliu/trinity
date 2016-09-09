package org.trinity.yqyl.repository.business.dataaccess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.Favorite;

public interface IFavoriteRepository extends CrudRepository<Favorite, Long>, PagingAndSortingRepository<Favorite, Long> {}