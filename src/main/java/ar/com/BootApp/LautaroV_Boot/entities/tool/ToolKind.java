package ar.com.BootApp.LautaroV_Boot.entities.tool;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tools-kind-")
public class ToolKind {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tool_kind")
    private String toolKind;
    @ManyToMany(mappedBy = "kind")
    @JsonIgnore
    private List<Tool> tools;

    public ToolKind(Long id, String toolKind) {
        this.id = id;
        this.toolKind = toolKind;
    }
}
