package tv.wanzami.model;

import java.time.Instant;

import jakarta.persistence.*;

@Entity
public class VideoProductionCrew {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
		
	@Column(name = "status", nullable = false, columnDefinition = "int(11) not null default 0")
	private Integer status;
			
	@Column(name = "video_id", nullable = false)
	private Integer videoId;

	@Column(name = "position", nullable = false, unique = false, length = 200)
	private String position;
	
	@Column(name = "name", nullable = false, unique = false, length = 200)
	private String name;
	
	@Column(name = "created_at", nullable = true)
	private Instant created_at;
	
	@Column(name = "updated_at", nullable = true)
	private Instant updated_at;
	
	public VideoProductionCrew() {
	}

	public VideoProductionCrew(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Instant getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Instant updated_at) {
		this.updated_at = updated_at;
	}

	public Instant getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Instant created_at) {
		this.created_at = created_at;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getVideo_id() {
		return videoId;
	}

	public void setVideo_id(Integer videoId) {
		this.videoId = videoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
