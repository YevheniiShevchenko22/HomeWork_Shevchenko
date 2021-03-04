package pl.edu.wszib.javaee.food.platform.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.javaee.food.platform.dao.ICustomerRestorationDAO;
import pl.edu.wszib.javaee.food.platform.exception.RestorationNotFoundException;
import pl.edu.wszib.javaee.food.platform.model.CustomerRestoration;
import pl.edu.wszib.javaee.food.platform.services.ICustomerRestorationService;

import java.util.List;

/**
 * Created by Yevhenii Shevchenko at 2/12/21
 * Project name: food.platform
 **/
@Service
public class CustomerRestorationServiceImpl implements ICustomerRestorationService {

    @Autowired
    ICustomerRestorationDAO customerRestorationDAO;

    @Override
    public void saveRestoration(CustomerRestoration restoration) {
        this.customerRestorationDAO.save(restoration);
    }

    @Override
    public CustomerRestoration getRestorationById(Long id) {
        return this.customerRestorationDAO.findCustomerRestorationById(id)
                .orElseThrow(()->
                        new RestorationNotFoundException("Restoration by id " + id + " was not found!!!"));
    }

    @Override
    public void updateRestorante(CustomerRestoration restoration) {
        this.customerRestorationDAO.save(restoration);
    }

    @Override
    public List<CustomerRestoration> getAllRestoration() {
        return this.customerRestorationDAO.findAll();
    }
}
