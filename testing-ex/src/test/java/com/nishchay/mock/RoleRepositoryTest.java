package com.nishchay.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RoleRepositoryTest {
    @Spy
    private RoleRepository roleRepository = new RoleRepository();

    @Test
    public void test() {
        roleRepository.getRole("toto");
        Mockito.verify(roleRepository, Mockito.times(1)).getRole(Mockito.anyString());
    }
}