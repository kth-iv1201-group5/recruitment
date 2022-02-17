package kth.iv1201.recruitment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Availability entity
 * Which contains information of Availability object.
 * It has annotation of <code>@Entity, @Data, @NoArgsConstructor, @AllArgsConstructor, @Builder, @Table</code> for better and consist readability.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "availability")
public class Availability {
	@Id
	@Column(name = "availability_id")
	private Integer id;
	@Column(name = "person_id")
	private Integer personId;
	@Column(name = "from_date")
	private LocalDate fromDate;
	@Column(name = "to_date")
	private LocalDate toDate;

	/**
	 * Display the period from start to end date.
	 *
	 * @return return string of start and end date.
	 */
	public String getPeriod() {
		return fromDate.toString() + " - " + toDate.toString();
	}

	/**
	 * Used for setting the CTA button active.
	 *
	 * @return if the toDate is in current year.
	 */
	public boolean isActive() {
		return toDate.getYear() == 2021; // TODO Change to current year dynamically but database is only to 2021.
	}
}
