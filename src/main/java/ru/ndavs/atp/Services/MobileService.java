//package ru.ndavs.atp.Services;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ru.ndavs.atp.MobileDTO.PassengersControlRequestDTO;
//import ru.ndavs.atp.MobileDTO.TimeControlRequestDTO;
//import ru.ndavs.atp.Repositories.DriverRepository;
//import ru.ndavs.atp.Repositories.RoadRepository;
//import ru.ndavs.atp.Repositories.TicketRepository;
//import ru.ndavs.atp.models.RoadStation;
//import ru.ndavs.atp.models.Road;
//import ru.ndavs.atp.models.Ticket;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.concurrent.atomic.AtomicReference;
//
//@Service
//public class MobileService {
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @Autowired
//    private DriverRepository driverRepository;
//
//    @Autowired
//    private TicketRepository ticketRepository;
//
//    @Autowired
//    private RoadRepository roadRepository;
//
//    public void markPassengers (PassengersControlRequestDTO passengers){
//        passengers.getTickets_id().stream()
//                .map(string_id -> {
//                    Long id = Long.parseLong(string_id);
//                    Optional<Ticket> ticket = ticketRepository.findById(id);
//                    if (ticket.isEmpty()) throw new IllegalStateException("The ticket was not found");
//                    ticket.get().setIs_visited(true);
//                    return id;
//                });
//    }
//    public void markTime (TimeControlRequestDTO time){
//        Optional<Road> road = roadRepository.findById(time.getRoute_id());
//        if (road.isEmpty()) throw new IllegalStateException("The route does not exist");
//
////        road.get().getGroup().getStations();
//
//        AtomicReference<Integer> index = new AtomicReference<>(0);
//
//        List<Integer> groupList = road.get().getGroup().getStations().stream().map(RoadStation::getTime).toList();
//        List<Integer> timeList = time.getTimes().stream().map(Integer::parseInt).toList();
//
////        road.get().getGroup().getStations().stream().map(groupStation -> {
////            groupStation.setTime(Integer.parseInt(time.getTimes().get(index.get())));
////            index.getAndSet(index.get() + 1);
////            return true;
////        });
//    }
//}
