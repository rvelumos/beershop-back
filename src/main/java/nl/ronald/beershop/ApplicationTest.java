package nl.ronald.beershop;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import nl.ronald.beershop.model.Address;
import nl.ronald.beershop.model.Product;
import nl.ronald.beershop.service.AddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest
{
    @InjectMocks
    AddressService addressService;

    @Mock
    Address addressMock;

    @Mock
    Product productMock;

    @Test
    public void getAddressTest()
    {
        Address saved = addressService.getAddress(1);
        assertEquals(true, saved);

//        verify(addressMock, times(1)).getAddress(1);
//        verify(productMock, times(1)).save("temp.txt");
    }
}