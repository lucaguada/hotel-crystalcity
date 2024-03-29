package hotel.crystalcity.cqrs.model.domain.booking;

import hotel.crystalcity.cqrs.acl.BookingShift;
import hotel.crystalcity.cqrs.api.model.domain.Aggregates;
import hotel.crystalcity.cqrs.api.port.EventSource;
import hotel.crystalcity.cqrs.api.port.egress.Storage;
import hotel.crystalcity.cqrs.api.port.egress.dto.entity.BookingDto;
import hotel.crystalcity.cqrs.model.value.Room;

import java.util.Optional;

public record Bookings(Storage<Short, BookingDto> storage, EventSource source) implements Aggregates<Room.Number, BookingAggregate>, BookingShift {
  @Override
  public void save(BookingAggregate booking) {
    storage.save(fromEntity(booking));
    source.emit(booking.changes());
  }

  @Override
  public Optional<BookingAggregate> load(Room.Number id) {
    return storage.findBy(id.value()).map(this::fromDto);
  }
}
