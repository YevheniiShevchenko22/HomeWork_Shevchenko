package pl.edu.wszib.javaee.food.platform.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wszib.javaee.food.platform.dao.IDishDAO;
import pl.edu.wszib.javaee.food.platform.exception.DishNotFoundExeption;
import pl.edu.wszib.javaee.food.platform.model.Dish;
import pl.edu.wszib.javaee.food.platform.model.FileUploadUtil;
import pl.edu.wszib.javaee.food.platform.services.IDishService;
import pl.edu.wszib.javaee.food.platform.session.SessionObject;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by Yevhenii Shevchenko at 2/11/21
 * Project name: food.platform
 **/
@Service
public class DishServiceImpl implements IDishService {

    @Autowired
    IDishDAO dishDAO;

    @Override
    public Dish getDishByID(Long id) {
        return this.dishDAO.findDishById(id)
                .orElseThrow(()-> new DishNotFoundExeption("Dish by id: "+id+" not found!!!"));
    }

    @Override
    public void updateDish(Dish dish) {
        this.dishDAO.save(dish);
    }

    @Override
    public void saveDish(Dish dish, MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        dish.setPicture(fileName);
        this.dishDAO.save(dish);
        String uploadDir = "src/main/resources/static/images/dishes/";
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    }

    @Override
    public void deleteDishById(Long id) {
        this.dishDAO.deleteById(id);
    }

    @Override
    public List<Dish> getAllDishes() {
        return this.dishDAO.findAll();
    }

}
