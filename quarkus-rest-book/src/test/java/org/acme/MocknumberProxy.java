package org.acme;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import io.quarkus.test.Mock;

@Mock
@RestClient
public class MocknumberProxy implements NumberProxy {

    @Override
    public IsbnThirteen generateIsbnNumbers() {
        IsbnThirteen isbnThirteen = new IsbnThirteen();
        isbnThirteen.isbn = "13-mock";

        return isbnThirteen;
    }
    
}
