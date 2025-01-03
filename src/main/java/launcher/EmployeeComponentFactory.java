package launcher;

import controller.BookController;
import database.DatabaseConnectionFactory;
import javafx.stage.Stage;
import mapper.BookMapper;
import mapper.OrderMapper;
import repository.book.BookRepository;
import repository.book.BookRepositoryMySQL;
import repository.order.OrderRepository;
import repository.order.OrderRepositoryMySQL;
import service.book.BookService;
import service.book.BookServiceImpl;
import service.order.OrderService;
import service.order.OrderServiceImpl;
import view.BookView;
import view.model.BookDTO;
import view.model.OrderDTO;

import java.sql.Connection;
import java.util.List;

//o clasa singleton
public class EmployeeComponentFactory {

    private final BookView bookView;
    private final BookController bookController;
    private final BookRepository bookRepository;
    private final BookService bookService;
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private static EmployeeComponentFactory instance;

    //lazy initialization
    public static EmployeeComponentFactory getInstance(Boolean componentsForTest, Stage primaryStage) {
        if(instance == null) {
            synchronized (EmployeeComponentFactory.class) {
                if(instance == null) {
                    instance = new EmployeeComponentFactory(componentsForTest, primaryStage);
                }
            }
        }
        return instance;
    }

    //controllerul va comunica mereu cu service, nu are parte de logica
    //partea de logica este in model
    //problema ca-l facem public aici ? -> DA, metoda vrem sa fie apelata doar din interiorul lui getInstance
    private EmployeeComponentFactory(Boolean componentsForTest, Stage primaryStage) {
        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(componentsForTest).getConnection();
        this.bookRepository = new BookRepositoryMySQL(connection);
        this.bookService = new BookServiceImpl(bookRepository);

        this.orderRepository = new OrderRepositoryMySQL(connection);
        this.orderService = new OrderServiceImpl(orderRepository);

        List<BookDTO> bookDTOS = BookMapper.convertBookListToBookDTOList(bookService.findAll());
        List<OrderDTO> orderDTOS = OrderMapper.convertOrderListToOrderDTOList(orderService.findAll());
        this.bookView = new BookView(primaryStage, bookDTOS, orderDTOS);

        this.bookController = new BookController(bookView, bookService, orderService);

    }

    public BookView getBookView() {
        return bookView;
    }

    public BookController getBookController() {
        return bookController;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public BookService getBookService() {
        return bookService;
    }
}
